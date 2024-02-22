import { Link } from "react-router-dom";

export default function NavBar() {
    return (
        <nav className="navbar flex flex-row justify-end space-x-10">
            <li>
                <Link to={"/"}>Home</Link>
            </li>
            <li>
                <Link to={"/journeys"}>Journeys</Link>
            </li>
            <li>
                <Link to={"/journeys/search"}>Search</Link>
            </li>
        </nav>
    );
}