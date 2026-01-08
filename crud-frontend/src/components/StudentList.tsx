import { useState } from "react";
import {
  getAllStudents,
  deleteStudent,
  getStudentsByDepartment,
} from "../services/studentService";
import StudentUpdateForm from "./StudentUpdateForm";

interface Student {
  id?: string;
  name: string;
  email: string;
  department: string;
}

export default function StudentList() {
  const [students, setStudents] = useState<Student[]>([]);
  const [editingStudent, setEditingStudent] = useState<Student | null>(null);
  const [department, setDepartment] = useState("");

  const loadStudents = async () => {
    const res = await getAllStudents();
    setStudents(res.data);
  };

  const handleDelete = async (id: string) => {
    await deleteStudent(id);
    alert("🗑️ Student deleted successfully");
    loadStudents();
  };

  const handleSearch = async () => {
    if (!department.trim()) return;
    const res = await getStudentsByDepartment(department);
    setStudents(res.data);
  };

  return (
    <div className="card">
      <h2>Student List</h2>

      <div className="search-bar">
        <input
          placeholder="Department"
          value={department}
          onChange={(e) => setDepartment(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
        <button className="secondary" onClick={loadStudents}>
          Show All
        </button>
      </div>

      {students.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Department</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {students.map((s) => (
              <tr key={s.id}>
                <td>{s.name}</td>
                <td>{s.email}</td>
                <td>{s.department}</td>
                <td>
                  <button onClick={() => setEditingStudent(s)}>Edit</button>
                  <button className="danger" onClick={() => handleDelete(s.id!)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {editingStudent && (
        <StudentUpdateForm
          student={editingStudent}
          onCancel={() => setEditingStudent(null)}
          onSaved={loadStudents}
        />
      )}
    </div>
  );
}
