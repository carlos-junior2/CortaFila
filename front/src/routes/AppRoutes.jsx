/* src\routes\AppRoutes.jsx */

import { Routes, Route, Navigate } from 'react-router-dom';
import { ROUTES } from './constantsRoutes';

/* Paginas publicas */
import Login from '../pages/public/Login/Login';
import Index from '../pages/public/Index/Index';

export default function AppRoutes() {
    return (
        <Routes>
            {/* Rotas públicas */}
            <Route path={ROUTES.INDEX} element={<Index />} />
            <Route path={ROUTES.LOGIN} element={<Login />} />
        </Routes>
    );
}