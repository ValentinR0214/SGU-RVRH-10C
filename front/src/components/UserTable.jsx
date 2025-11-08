import React from 'react';
import { Table, Button, Stack } from 'react-bootstrap';
import { PencilFill, TrashFill } from 'react-bootstrap-icons';

const UserTable = ({ users, onEdit, onDelete }) => {
  return (
    <Table striped bordered hover responsive="sm">
      <thead className="table-dark">
        <tr>
          <th>#</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Email</th>
          <th>Teléfono</th>
          <th className="text-center">Acciones</th>
        </tr>
      </thead>
      <tbody>
        {users.length === 0 ? (
          <tr>
            <td colSpan="6" className="text-center">No hay usuarios registrados</td>
          </tr>
        ) : (
          users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.nombre}</td>
              <td>{user.apellido}</td>
              <td>{user.email}</td>
              <td>{user.telefono}</td>
              <td>
                <Stack direction="horizontal" gap={2} className="justify-content-center">
                  <Button variant="warning" size="sm" onClick={() => onEdit(user)}>
                    <PencilFill /> Editar
                  </Button>
                  <Button variant="danger" size="sm" onClick={() => onDelete(user.id)}>
                    <TrashFill /> Eliminar
                  </Button>
                </Stack>
              </td>
            </tr>
          ))
        )}
      </tbody>
    </Table>
  );
};

export default UserTable;