import Navbar from "@/components/NavBar";
import { Outlet } from "react-router-dom";

export default function Root() {
    return (
        <div className='page-wrapper w-full h-full'>
            <Navbar />
            <Outlet />
        </div>
    );
}