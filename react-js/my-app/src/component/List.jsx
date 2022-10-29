import studentApi from "../api/studentApi";
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import Update from "./Update";
import { useNavigate } from "react-router-dom";

export default function List() {
    const [students, setStudents] = useState([])
    const [loading, setLoading] = useState(true)
    const [filters, setFilters] = useState({});

    const navigate = useNavigate();

    useEffect(() => {
        ; (async () => {
            try {
                const response = await studentApi.getAll();
                setStudents(response.data);
            } catch (error) {
                console.log(error.message);
            }
            setLoading(false)
        })()
    }, [filters])

    const studentsRow = students.map((student, count) =>
        <tr key={count}>
            <th scope="row">{count + 1}</th>
            <td>{student.studentCode}</td>
            <td>{student.studentFirstName}</td>
            <td>{student.studentLastName}</td>
            <td>{student.studentEmail}</td>
            <td>{student.studentDateOfBirth}</td>
            <td><button onClick={() => onUpdate(student.studentId)} type="submit" class="btn btn-primary">Edit</button></td>
            <td><button onClick={() => onDelete(student.studentId)} type="submit" class="btn btn-primary">Delete</button></td>
        </tr>
    );

    const onUpdate = (id) => {
       localStorage.setItem("id", id);
       navigate("/update");
    }

    const onDelete = async (id) => {
        await studentApi.remove(id);
        getStudents();
    }
    
    const getStudents = async() => {
        const res = await studentApi.getAll();
        setStudents(res.data);
    }

    return (
        <div>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Code</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Email</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {studentsRow}
                </tbody>
            </table>
        </div>
    );
}