import ArrowReturn from "../../../components/common/ArrowReturn/ArrowReturn";
import PublicHeader from "../../../components/common/PublicHeader/PublicHeader";
import { buscarBarbeariaPorId } from "../../../services/agendamentoServices";
import { ROUTES } from "../../../routes/constantsRoutes";
import favoritos from "../../../assets/favoritos.png";
import './BarbeariaPerfil.css';
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const BarbeariaPerfil = () => {
    const { id } = useParams();
    const [barbearia, setBarbearia] = useState(null)
    const [carregandoBarbearia, setCarregandoBarbearia] = useState(false);

    useEffect(() => {
        async function carregar() {
            try {
                const dados = await buscarBarbeariaPorId(id);
                setBarbearia(dados);
            } catch (error) {
                console.log(error);
            } finally {
                setCarregandoBarbearia(false);
            }
        }
        carregar();
    }, [id])

    return (
        <>
            <div className="container">
                <div className="header-container">
                    <ArrowReturn route={ROUTES.FEED} />
                    <PublicHeader icone={favoritos} />
                </div>
                <div className="info-group">
                    {carregandoBarbearia ? (
                        <p>Carregando barbearia...</p>
                    ) : (
                        <div className="perfil-barbearia">
                            <h1>{barbearia.nome}</h1>
                        </div>
                    )}
                </div>
            </div>

        </>

    )
}

export default BarbeariaPerfil;