import { Link } from "react-router-dom";
import { Button } from "./ui/button";

export default function Navbar() {
    return (
        <nav className="navbar flex flex-row list-none p-2">
            <div className="home-logo">
                <Link to={"/"}>Time Travel Company</Link>
            </div>
            <div className="nav-links flex flex-row flex-grow justify-end">
                <ul className="flex flex-row list-none space-x-10 items-center">
                    <li>
                        <Link to={"/journeys"}>Journeys</Link>
                    </li>
                    <li>
                        <Link to={"/journeys/search"}>Search</Link>
                    </li>
                    <li>
                        <Link to={"/journeys/982573"}>(Details)</Link>
                    </li>
                    <li>
                        Sign Up
                    </li>
                    <li>
                        <Button>Log In</Button>
                    </li>
                </ul>
            </div>
        </nav>
    );
}