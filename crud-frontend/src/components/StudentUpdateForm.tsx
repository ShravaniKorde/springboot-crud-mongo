import { useState } from "react";
import { updateStudent } from "../services/studentService";

interface Props {
  student: any;
  onCancel: () => void;
  onSaved: () => void;
}

export default function StudentUpdateForm({ student, onCancel, onSaved }: Props) {
  const [updated, setUpdated] = useState(student);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUpdated({ ...updated, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    await updateStudent(updated.id, updated);
    alert("✏️ Student updated successfully");
    onSaved();
    onCancel();
  };

  return (
    <div className="edit-card">
      <h3>Edit Student</h3>

      <div className="form-group">
        <input name="name" value={updated.name} onChange={handleChange} />
        <input name="email" value={updated.email} onChange={handleChange} />
        <input name="department" value={updated.department} onChange={handleChange} />
      </div>

      <div className="actions">
        <button onClick={handleSave}>Save</button>
        <button className="secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </div>
  );
}
