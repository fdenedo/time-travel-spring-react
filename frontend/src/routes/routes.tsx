import Root from '@/layout/Root';
import Home from '@/pages/Home';
import JourneyDetails from '@/pages/JourneyDetails';
import JourneySearch from '@/pages/JourneySearch';
import Journeys from '@/pages/Journeys';
import { Route, Routes } from 'react-router-dom';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Root />}>
        <Route index element={<Home />} />
        <Route path="/journeys" element={<Journeys />} />
        <Route path="/journeys/search" element={<JourneySearch />} />
        <Route path="/journeys/:id" element={<JourneyDetails />} />
      </Route>
    </Routes>
  );
};
  
export default AppRoutes;