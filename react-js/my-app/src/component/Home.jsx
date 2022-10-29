import studentApi from "../api/studentApi";
import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import List from "./List";

export default function Home() {
    return (
        <>
            <div class="container">
                <h5>Students List</h5>
                <List />
                <Link to="create"><button type="submit" class="btn btn-primary">Add</button></Link>
            </div>
        </>
    );
}