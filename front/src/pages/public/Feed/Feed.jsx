import './Feed.css';
import Footer from "../../../components/common/Footer/Footer";
import HeaderFeed from "../../../components/public/HeaderFeed/HeaderFeed";
import barberShop1 from "../../../assets/barberShop1.jpg";
import options from '../../../assets/options.png'
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { buscarBarbearia } from '../../../services/agendamentoServices';

const Feed = () => {

    const [barbearias, setBarbearias] = useState([]);
    const [carregando, setCarregando] = useState(false);
    const [pagina, setPagina] = useState(0);
    const [temMais, setTemMais] = useState(true);
    const navigate = useNavigate();

    async function carregarMais() {
        if (carregando || !temMais) return;
        setCarregando(true);

        try {
            const lista = await buscarBarbearia();
            if (lista.length === 0) {
                setTemMais(false);
            } else {
                setBarbearias(prev => [...prev, ...lista]);
                setPagina(prev => prev + 1);
            }
        } catch (err) {
            console.error("Erro ao carregar barbearias:", err);
        } finally {
            setCarregando(false);
        }
    }

    function selecionarBarbearia(id) {
        navigate(`/barbearia/${id}`); // leva para tela de perfil
    }

    useEffect(() => {
        carregarMais();
        const handleScroll = () => {
            if (window.innerHeight + document.documentElement.scrollTop >= document.documentElement.scrollHeight - 100) {
                carregarMais();
            }
        };
        window.addEventListener("scroll", handleScroll);
        return () => window.removeEventListener("scroll", handleScroll);

    }, []);

    return (
        <>
            <HeaderFeed titulo="CortaFila" icone={options} />
            <div className="feed">
                {barbearias.map(barbearia => (
                    <div className="card" key={barbearia.id}>
                        <img src={barberShop1} alt={barbearia.nome} />
                        <div className="card-content">
                            <h3>{barbearia.nome}</h3>
                            <p>⭐ 5.0 avaliações</p>
                            <p>📍 2.5 km</p>
                            <button onClick={() => selecionarBarbearia(barbearia.id)}>
                                Selecionar
                            </button>
                        </div>
                    </div>
                ))}
            </div>
            {carregando && <p style={{ textAlign: "center" }}>Carregando...</p>}
            {!temMais && <p style={{ textAlign: "center" }}>Todas as barbearias carregadas.</p>}
            <Footer />
        </>
    )

}

export default Feed;