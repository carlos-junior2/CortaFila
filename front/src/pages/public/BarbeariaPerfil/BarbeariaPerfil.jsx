import './BarbeariaPerfil.css';
import ArrowReturn from "../../../components/common/ArrowReturn/ArrowReturn";
import PublicHeader from "../../../components/common/PublicHeader/PublicHeader";
import { buscarBarbeariaPorId } from "../../../services/agendamentoServices";
import { ROUTES } from "../../../routes/constantsRoutes";
import favoritos from "../../../assets/favoritos.png";
import barberShop1 from "../../../assets/barberShop1.jpg";
import perfil from "../../../assets/perfil.png";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { FiCheck } from "react-icons/fi";

const BarbeariaPerfil = () => {
    const { id } = useParams();
    const [barbearia, setBarbearia] = useState('');
    const [barbeiros, setBarbeiros] = useState([]);
    const [carregandoBarbearia, setCarregandoBarbearia] = useState(false);
    const [barbeiroSelecionado, setBarbeiroSelecionado] = useState(null);

    useEffect(() => {
        async function carregar() {
            setCarregandoBarbearia(true);
            try {
                const dados = await buscarBarbeariaPorId(id);
                setBarbearia(dados);
                setBarbeiros(dados.barbeiros || []);
            } catch (error) {
                console.log(error);
            } finally {
                setCarregandoBarbearia(false);
            }
        }
        carregar();
    }, [id]);

    const handleBook = () => {
        console.log("Barbeiro selecionado:", barbeiroSelecionado);
        // Aqui pode redirecionar para outra tela ou salvar no backend
    };

    return (
        <div className="container">
            <div className="header-container">
                <ArrowReturn route={ROUTES.FEED} />
                <PublicHeader icone={favoritos} />
            </div>

            <div className="info-group">
                {carregandoBarbearia ? (
                    <p>Carregando barbearia...</p>
                ) : (
                    barbearia && (
                        <div className="perfil-barbearia">
                            <img src={barberShop1} alt="Foto da barbearia" />
                            <h1>{barbearia.nome}</h1>
                            <p>⭐ 5.0 avaliações</p>
                            <span>{barbearia.descricao}</span>
                        </div>
                    )
                )}
            </div>

            <div className="container-barbers">
                <h2>Escolha o profissional</h2>
                <div className="barbers-group">
                    {carregandoBarbearia ? (
                        <p>Carregando barbeiros...</p>
                    ) : (
                        barbeiros.length ? (
                            barbeiros.map(barbeiro => (
                                <div
                                    key={barbeiro.id}
                                    className={`barber-card ${barbeiroSelecionado === barbeiro.id ? "selected" : ""}`}
                                    onClick={() => setBarbeiroSelecionado(barbeiro.id)}
                                >
                                    <div className="barber-info">
                                        <img src={perfil} alt={barbeiro.nomeUsuario} />
                                        <span>{barbeiro.nomeUsuario}</span>
                                    </div>
                                    {barbeiroSelecionado === barbeiro.id && <FiCheck size={20} />}
                                </div>
                            ))
                        ) : (
                            <p>Nenhum barbeiro encontrado.</p>
                        )
                    )}
                </div>
            </div>

            <div className="footer-book">
                <button
                    className={`book-btn ${barbeiroSelecionado ? "enabled" : "disabled"}`}
                    disabled={!barbeiroSelecionado}
                    onClick={handleBook}
                >
                    Ver horários disponíveis
                </button>
            </div>
        </div>
    );
};

export default BarbeariaPerfil;
