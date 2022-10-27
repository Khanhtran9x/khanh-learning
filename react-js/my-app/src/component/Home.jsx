import studentApi from "../api/studentApi";
import React, { useEffect, useState } from 'react';

function Students() {
    const [students, setStudents] = useState([])
    const [loading, setLoading] = useState(true)
    const [filters, setFilters] = useState({});

    useEffect(() => {
        ;(async () => {
            try {
                const response = await studentApi.getAll();
                setStudents(response.data.results);
                console.log(response);
            } catch (error) {
                console.log(error.message);
            }
            setLoading(false)
        })()
    }, [filters])

    return (
        <div>
          <Student students={students}/>
        </div>
    );
}

function Student({students}) {
    // const studentsRow = students.foreach((student) =>
    //         <p>student</p>
    // );
    return;
}

export default function Home() {
    return (
        <>
            <div class = "container">
                <Students />
                <h5>Students List</h5>
                <div class="card" style={{width: "18rem"}}>
                    <image class="card-img-top" src="..." alt="Card image cap"></image>
                    <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div> b
            </div>
        </>
    );
}