import ArrowReturn from "../../../components/common/ArrowReturn/ArrowReturn";
import PublicHeader from "../../../components/common/PublicHeader/PublicHeader";
import { buscarBarbearia } from "../../../services/agendamentoServices";
import { ROUTES } from "../../../routes/constantsRoutes";
import favoritos from "../../../assets/favoritos.png";
import './BarbeariaPerfil.css';
import { useEffect } from "react";

const BarbeariaPerfil = () => {

    const [carregandoBarbearia, setCarregandoBarbearia] = useState(false);

    useEffect(() => {
        async function carregarBarbearia() {
            setCarregandoBarbearia(true);
            try {
                const lista = await buscarBarbearia();
                setBarbearia(lista);
            } catch (err) {
                console.error('Erro ao carregar barbeiros:', err);
            } finally {
                setCarregandoBarbearia(false);
            }
        }

        carregarBarbearia();
    }, []);

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
                        <div>

                        </div>
                    )}
                </div>
            </div>

        </>

    )
}

export default BarbeariaPerfil;