import axios from "axios";

const API_URL = "http://localhost:8081/students";

export interface Student {
  id?: string;
  name: string;
  email: string;
  department: string;
}

export const getAllStudents = () =>
  axios.get<Student[]>(API_URL);

export const createStudent = (student: Student) =>
  axios.post(API_URL, student);

export const updateStudent = (id: string, student: Student) =>
  axios.put(`${API_URL}/${id}`, student);

export const deleteStudent = (id: string) =>
  axios.delete(`${API_URL}/${id}`);

export const getStudentsByDepartment = (dept: string) =>
  axios.get<Student[]>(`${API_URL}/department/${dept}`);
