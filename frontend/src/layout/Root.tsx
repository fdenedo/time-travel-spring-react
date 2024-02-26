import Navbar from "@/components/NavBar";
import { Outlet } from "react-router-dom";

export default function Root() {
    return (
        <div className='page-wrapper w-full h-full'>
            <Navbar />
            <div className="content-main flex flex-col mt-4">
                <Outlet />
            </div>
        </div>
    );
}