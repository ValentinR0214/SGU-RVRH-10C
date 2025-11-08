package mx.edu.utez.Server.users.control;

import mx.edu.utez.Server.users.model.User;
import mx.edu.utez.Server.users.model.UserDTO;
import mx.edu.utez.Server.users.model.UserRepository;
import mx.edu.utez.Server.utils.Message;
import mx.edu.utez.Server.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("La búsqueda de usuarios ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(users, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(UserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            return new ResponseEntity<>(new Message("El email ya está registrado", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());

        user = userRepository.saveAndFlush(user);
        if(user == null) { // Esta validación está bien
            return new ResponseEntity<>(new Message("El usuario no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro de usuario ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(user, "El usuario se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(UserDTO dto) {

        Optional<User> userOptional = userRepository.findById(dto.getId());
        if(!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Optional<User> userByEmail = userRepository.findByEmail(dto.getEmail());
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(dto.getId())) {
            return new ResponseEntity<>(new Message("El email ya está registrado por otro usuario", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        User user = userOptional.get();
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());

        user = userRepository.saveAndFlush(user);
        if(user == null) {
            return new ResponseEntity<>(new Message("El usuario no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización de usuario ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(user, "El usuario se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);
        return new ResponseEntity<>(new Message("Usuario eliminado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}