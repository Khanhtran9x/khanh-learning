import { useEffect } from "react";
import studentApi from "../api/studentApi";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

export default function Update() {
    const [studentCode, setStudentCode] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [dateOfBirth, studentDateOfBirth] = useState('');
    const [loading, setLoading] = useState(true);
    const [filters, setFilters] = useState({});

    const navigate = useNavigate();
    const id = localStorage.getItem("id");

    useEffect(() => {
        ; (async () => {
            try {
                const response = await studentApi.get(id);
                setStudentCode(response.data.studentCode);
                setFirstName(response.data.studentFirstName);
                setLastName(response.data.studentLastName);
                setEmail(response.data.studentEmail);
                studentDateOfBirth(response.data.studentDateOfBirth);
            } catch (error) {
                console.log(error.message);
            }
            setLoading(false);
        })()
    }, [filters])

    const postData = async (event) => {
        event.preventDefault();
        const student = {
            "studentId": id,
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
            <h4>Update student</h4>
            <form>
                <div className="form-group">
                    <label htmlFor="code">Student code</label>
                    <input type="text" value={studentCode} className="form-control" id="code" placeholder="Enter student code"
                        onChange={(e) => setStudentCode(e.target.value)} />
                </div>
                <div class="form-group">
                    <label htmlFor="first">First name</label>
                    <input type="text" value={firstName} className="form-control" id="first" placeholder="Enter first name"
                        onChange={(e) => setFirstName(e.target.value)} />
                </div>
                <div className="form-group">
                    <label htmlFor="last">Last name</label>
                    <input type="text" value={lastName} className="form-control" id="last" placeholder="Enter last name"
                        onChange={(e) => setLastName(e.target.value)} />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input type="text" value={email} className="form-control" id="email" placeholder="Enter email"
                        onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="form-group">
                    <label htmlFor="dob">Date of birth</label>
                    <input type="date" value={dateOfBirth} className="form-control" id="dob"
                        onChange={(e) => studentDateOfBirth(e.target.value)} />
                </div>
                <button onClick={postData} className="btn btn-primary mr-2">Update</button>
                <Link to="/"><button className="btn btn-secondary">Back</button></Link>
            </form>
        </div>
    );
}