/* src\routes\AppRoutes.jsx */

import { Routes, Route, Navigate } from 'react-router-dom';
import { ROUTES } from './constantsRoutes';

/* Paginas publicas */
import Login from '../pages/public/Login/Login';
import Index from '../pages/public/Index/Index';
import Home from '../pages/public/home/Home';
import NovoAgendamento from '../pages/private/NovoAgendamento/NovoAgendamento';
import Feed from '../pages/public/Feed/Feed';
import BarbeariaPerfil from '../pages/public/BarbeariaPerfil/BarbeariaPerfil';

export default function AppRoutes() {
    return (
        <Routes>
            {/* Rotas públicas */}
            <Route path={ROUTES.INDEX} element={<Index />} />
            <Route path={ROUTES.LOGIN} element={<Login />} />
            <Route path={ROUTES.HOME} element={<Home />} />
            <Route path={ROUTES.FEED} element={<Feed />} />
            <Route path={ROUTES.BARBER} element={<BarbeariaPerfil />} />

            {/* Rotas privadas */}
            <Route path={ROUTES.AGENDAMENTO} element={<NovoAgendamento />} />
        </Routes>
    );
}