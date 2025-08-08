import { Link } from 'react-router-dom';
import { ROUTES } from '../../../routes/constantsRoutes';
import agendar from '../../../assets/agendar.png';
import poste from '../../../assets/poste.png';
import home from '../../../assets/home.png';
import ativo from '../../../assets/ativo.png';
import perfil from '../../../assets/perfil.png';
import './Footer.css';

const Footer = () => {
    return (
        <>
            <footer class="bottom-nav">
                <Link to={ROUTES.HOME}>
                    <div class="nav-item active">
                        <img src={home} alt='inicio' className='icone' />
                        <span>Inicío</span>
                    </div>
                </Link>

                <Link to={ROUTES.AGENDAMENTO}>
                    <div class="nav-item">
                        <img src={agendar} alt='agendar' className='icone' />
                        <span>Agendamentos</span>
                    </div>
                </Link>
                <Link to={ROUTES.FEED}>
                    <div class="nav-item">
                        <img src={poste} alt='feed' className='icone' />
                        <span>Barbearias </span>
                    </div>
                </Link>
                <Link to={ROUTES.NOTIFICACOES}>
                    <div class="nav-item">
                        <img src={ativo} alt="notificacao" className='icone' />
                        <span>Notifications</span>
                    </div>
                </Link>
                <Link to={ROUTES.PERFIL}>
                    <div class="nav-item">
                        <img src={perfil} alt="perfil" className='icone' />
                        <span>Perfil</span>
                    </div>
                </Link>
            </footer>
        </>
    )
}

export default Footer;