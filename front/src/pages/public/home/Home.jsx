import Header from '../../../components/common/Header/Header';
import Container from '../../../components/common/Container/Container';
import Card from '../../../components/common/Card/Card';
import './home.css';
import { ROUTES } from '../../../routes/constantsRoutes';
import { Link } from 'react-router-dom';
import agendar from '../../../assets/agendar.png';
import poste from '../../../assets/poste.png';
import home from '../../../assets/home.png';
import ativo from '../../../assets/ativo.png';
import perfil from '../../../assets/perfil.png';

/**
 * Página Home
 * 
 * Página inicial da aplicação onde o usuário visualiza informações principais como destaques de barbearias,
 * navegação entre funcionalidades e seções populares do sistema.
 * 
 * @component
 * @returns {JSX.Element} Estrutura da página inicial, incluindo cabeçalho, cards, lista de barbeiros populares e menu inferior de navegação.
 * 
 * @example
 * <Home />
 * 
 * @description
 * 
 * - Inclui os seguintes componentes principais:
 *   - **Header**: Componente de cabeçalho reutilizável presente nas principais páginas da aplicação.
 *   - **Container**: Estrutura que centraliza e define limites de largura para o conteúdo principal.
 *   - **Card**: Card com informações principais (ex: próximo agendamento ou promoções).
 *   - **Seção de barbeiros populares**: Lista de perfis visuais representando barbeiros mais acessados.
 *   - **Bottom Navigation**: Menu fixo inferior com ícones para navegação rápida entre as principais seções.
 * 
 * @see Header
 * @see Container
 * @see Card
 */


const Home = () => {
    return (
        <>
            <Header titulo="Bem vindo!" />
            <Container>
                <Card />
                <section class="barbers">
                    <h2>Popular barbers</h2>
                    <div class="avatars">
                        <div class="avatar"></div>
                        <div class="avatar"></div>
                        <div class="avatar"></div>
                    </div>
                </section>
            </Container>
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

export default Home;