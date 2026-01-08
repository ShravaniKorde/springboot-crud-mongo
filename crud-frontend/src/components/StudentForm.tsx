import { useState } from "react";
import { createStudent } from "../services/studentService";

export default function StudentForm({ onCreated }: { onCreated: () => void }) {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    department: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    await createStudent(student);
    alert("✅ Student created successfully");
    setStudent({ name: "", email: "", department: "" });
    onCreated();
  };

  return (
    <div className="card">
      <h2>Create Student</h2>

      <div className="form-group">
        <input name="name" placeholder="Name" value={student.name} onChange={handleChange} />
        <input name="email" placeholder="Email" value={student.email} onChange={handleChange} />
        <input
          name="department"
          placeholder="Department"
          value={student.department}
          onChange={handleChange}
        />
      </div>

      <button onClick={handleSubmit}>Create</button>
    </div>
  );
}
