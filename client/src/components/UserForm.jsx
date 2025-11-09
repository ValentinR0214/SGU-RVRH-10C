import React, { useState, useEffect } from 'react';
import { Modal, Button, Form, Row, Col } from 'react-bootstrap';

const initialFormData = {
    id: null,
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
};

const UserForm = ({ show, onHide, onSubmit, initialData }) => {
    const [formData, setFormData] = useState(initialFormData);

    const [validated, setValidated] = useState(false);

    const isEditMode = initialData && initialData.id;

    useEffect(() => {
        if (show) {
            if (isEditMode) {
                setFormData(initialData);
            } else {
                setFormData(initialFormData);
            }
            setValidated(false);
        }
    }, [initialData, show]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleClose = () => {
        setValidated(false);
        onHide();
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const form = e.currentTarget;

        if (form.checkValidity() === false) {
            e.stopPropagation();
            setValidated(true);
            return;
        }

        setValidated(true);
        onSubmit(formData);
        handleClose();
    };

    return (
    <Modal show={show} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>{isEditMode ? 'Editar Usuario' : 'Registrar Nuevo Usuario'}</Modal.Title>
      </Modal.Header>
      
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Modal.Body>
          <Row>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formNombre">
                <Form.Label>Nombre</Form.Label>
                <Form.Control
                  type="text"
                  name="nombre"
                  value={formData.nombre}
                  onChange={handleChange}
                  required 
                />
                <Form.Control.Feedback type="invalid">
                  El nombre es obligatorio.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group className="mb-3" controlId="formApellido">
                <Form.Label>Apellido</Form.Label>
                <Form.Control
                  type="text"
                  name="apellido"
                  value={formData.apellido}
                  onChange={handleChange}
                  required
                />
                <Form.Control.Feedback type="invalid">
                  El apellido es obligatorio.
                </Form.Control.Feedback>
              </Form.Group>
            </Col>
          </Row>
          <Form.Group className="mb-3" controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email" 
              name="email"
              placeholder="ejemplo@correo.com"
              value={formData.email}
              onChange={handleChange}
              required
            />
            <Form.Control.Feedback type="invalid">
              Por favor, ingresa un email válido.
            </Form.Control.Feedback>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formTelefono">
            <Form.Label>Teléfono</Form.Label>
            <Form.Control
              type="tel" 
              name="telefono"
              value={formData.telefono}
              onChange={handleChange}
              required
              maxLength={10} 
              pattern="[0-9]{10}" 
            />
            <Form.Control.Feedback type="invalid">
              El teléfono debe tener exactamente 10 dígitos (sin espacios ni guiones).
            </Form.Control.Feedback>
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancelar
          </Button>
          <Button variant="primary" type="submit">
            {isEditMode ? 'Actualizar' : 'Guardar'}
          </Button>
        </Modal.Footer>
      </Form>
    </Modal>
  );
};

export default UserForm;