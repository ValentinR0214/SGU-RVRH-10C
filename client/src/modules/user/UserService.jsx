import axios from 'axios';

const ENV = import.meta.env;

const API_URL = `${ENV.VITE_API_PROTOCOL}://${ENV.VITE_API_HOST}:${ENV.VITE_API_PORT}${ENV.VITE_API_BASE}`;

const apiClient = axios.create({
  baseURL: API_URL,
});


export const getAllUsers = async () => {
  try {
    const response = await apiClient.get('/');
    return response.data.result; 
  } catch (error) {
    console.error("Error al obtener usuarios:", error);
    throw error;
  }
};

export const saveUser = async (user) => {
  try {
    const { nombre, apellido, email, telefono } = user;
    const response = await apiClient.post('/save', { nombre, apellido, email, telefono });
    return response.data;
  } catch (error) {
    console.error("Error al guardar usuario:", error);
    throw error;
  }
};

export const updateUser = async (user) => {
  try {
    const { id, nombre, apellido, email, telefono } = user;
    const response = await apiClient.put('/update', { id, nombre, apellido, email, telefono });
    return response.data;
  } catch (error) {
    console.error("Error al actualizar usuario:", error);
    throw error;
  }
};

export const deleteUser = async (id) => {
  try {
    const response = await apiClient.delete(`/delete/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error al eliminar usuario:", error);
    throw error;
  }
};