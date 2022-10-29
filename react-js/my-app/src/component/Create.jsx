import React from 'react'
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import studentApi from "../api/studentApi";

export default function Create() {
    const [studentCode, setStudentCode] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [dateOfBirth, studentDateOfBirth] = useState('');

    const navigate = useNavigate();

    const postData = async (event) => {
        event.preventDefault();
        const student = {
            "studentId": null,
            "studentCode": studentCode,
            "studentFirstName": firstName,
            "studentLastName": lastName,
            "studentDateOfBirth": dateOfBirth,
            "studentEmail": email,
            "classEntity": {
                "classId": 1,
                "className": "10A1"
            }
        }
        await studentApi.add(student);
        navigate('/');
    }

    return (
        <div className='container'>
            <h4>Create new student</h4>
            <form>
                <div className="form-group">
                    <label htmlFor="code">Student code</label>
                    <input type="text" className="form-control" id="code" placeholder="Enter student code"
                     onChange={(e) => setStudentCode(e.target.value)}/>
                </div>
                <div class="form-group">
                    <label htmlFor="first">First name</label>
                    <input type="text" className="form-control" id="first" placeholder="Enter first name" 
                    onChange={(e) => setFirstName(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="last">Last name</label>
                    <input type="text" className="form-control" id="last" placeholder="Enter last name" 
                    onChange={(e) => setLastName(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input type="text" className="form-control" id="email" placeholder="Enter email" 
                    onChange={(e) => setEmail(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="dob">Date of birth</label>
                    <input type="date" className="form-control" id="dob" 
                    onChange={(e) => studentDateOfBirth(e.target.value)}/>
                </div>
                <button onClick={postData} className="btn btn-primary mr-2">Create</button>
                <Link to="/"><button className="btn btn-secondary">Back</button></Link>
            </form>
        </div>
    );
}