import React, { useState, useEffect } from 'react';
import { Container, Card, Button, Spinner } from 'react-bootstrap';
import { PlusCircleFill } from 'react-bootstrap-icons';
import UserTable from '../../components/UserTable';
import UserForm from '../../components/UserForm';
import * as UserService from './UserService';
import * as Alerts from '../../utils/Alerts'; 
const UserModal = () => {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      setLoading(true);
      const data = await UserService.getAllUsers();
      setUsers(data || []);
    } catch (err) {
      Alerts.showErrorAlert('No se pudieron cargar los usuarios.');
    } finally {
      setLoading(false);
    }
  };

  const handleShowModal = () => setShowModal(true);

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedUser(null);
  };

  const handleEditClick = (user) => {
    setSelectedUser(user);
    handleShowModal();
  };

  const handleCreateClick = () => {
    setSelectedUser(null);
    handleShowModal();
  };


  const handleSubmitForm = async (formData) => {
    const isUpdating = !!formData.id;

    try {
      if (isUpdating) {
        await UserService.updateUser(formData);
      } else {
        await UserService.saveUser(formData);
      }
      fetchUsers();
      handleCloseModal();

      const message = isUpdating ? 'Usuario actualizado' : 'Usuario registrado';
      Alerts.showSuccessToast(message);

    } catch (err) {
      const errorMessage = err.response?.data?.text || 'No se pudo guardar el usuario.';
      Alerts.showErrorAlert(errorMessage);
    }
  };

  const handleDeleteClick = async (id) => {

    const result = await Alerts.showConfirmDelete();


    if (result.isConfirmed) {
      try {
        await UserService.deleteUser(id);
        fetchUsers();
        Alerts.showSuccessToast('Usuario eliminado');
      } catch (err) {
        const errorMessage = err.response?.data?.text || 'No se pudo eliminar el usuario.';
        Alerts.showErrorAlert(errorMessage);
      }
    }
  };

  return (
    <Container className="mt-4">
      <Card>
        <Card.Header className="d-flex justify-content-between align-items-center">
          <h2 className="mb-0">Gestión de Usuarios</h2>
          <Button variant="primary" onClick={handleCreateClick}>
            <PlusCircleFill className="me-2" /> Nuevo Usuario
          </Button>
        </Card.Header>
        <Card.Body>
          {loading ? (
            <div className="text-center">
              <Spinner animation="border" variant="primary" />
              <p>Cargando usuarios...</p>
            </div>
          ) : (
            <UserTable
              users={users}
              onEdit={handleEditClick}
              onDelete={handleDeleteClick}
            />
          )}
        </Card.Body>
      </Card>

      <UserForm
        show={showModal}
        onHide={handleCloseModal}
        onSubmit={handleSubmitForm}
        initialData={selectedUser}
      />
    </Container>
  );
};

export default UserModal;