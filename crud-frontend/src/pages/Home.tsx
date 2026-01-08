import StudentForm from "../components/StudentForm";
import StudentList from "../components/StudentList";
import { useState } from "react";

export default function Home() {
  const [refreshKey, setRefreshKey] = useState(0);

  const handleCreated = () => {
    setRefreshKey((prev) => prev + 1);
  };

  return (
    <div className="container">
      <h2>Student Management System</h2>

      <StudentForm onCreated={handleCreated} />

      {/* key forces re-render only when needed */}
      <StudentList key={refreshKey} />
    </div>
  );
}
