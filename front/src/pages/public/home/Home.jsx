import Header from '../../../components/common/Header/Header';
import Container from '../../../components/common/Container/Container';
import Card from '../../../components/common/Card/Card';
import Footer from '../../../components/common/Footer/Footer';
import './home.css';


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
            <Footer />
        </>

    )
}

export default Home;