import ArrowReturn from "../../../components/common/ArrowReturn/ArrowReturn";
import Header from "../../../components/common/Header/Header";
import { buscarBarbearia, buscarBarbeirosPorBarbearia, buscarServicos } from "../../../services/agendamentoServices";
import './NovoAgendamento.css';
import { useState, useEffect } from "react";
import { ROUTES } from "../../../routes/constantsRoutes";
import { useLocation } from "react-router-dom";

const NovoAgendamento = () => {
    const location = useLocation();
    const barbeariaState = location.state?.barbearia;
    const barbeiroState = location.state?.barbeiro;

    const [barbearias, setBarbearia] = useState([]);
    const [barbeariaSelecionada, setBarbeariaSelecionada] = useState('');
    const [carregandoBarbearia, setCarregandoBarbearia] = useState(false);

    const [barbeiroSelecionado, setBarbeiroSelecionado] = useState('');
    const [barbeiros, setBarbeiros] = useState([]);

    const [servicos, setServicos] = useState([]);
    const [servicoSelecionado, setServicoSelecionado] = useState('');

    const [agenda, setAgenda] = useState([]);

    const [diaSelecionado, setDiaSelecionado] = useState('');

    useEffect(() => {
        async function carregarBarbearias() {
            setCarregandoBarbearia(true);
            try {
                const lista = await buscarBarbearia();
                setBarbearia(lista);

                // Se veio da tela anterior, já seleciona a barbearia e busca barbeiros
                if (barbeariaState) {
                    setBarbeariaSelecionada(barbeariaState.id);
                    const listaBarbeiros = await buscarBarbeirosPorBarbearia(barbeariaState.id);
                    setBarbeiros(listaBarbeiros);

                    // Se também veio barbeiro, já seleciona
                    if (barbeiroState) {
                        setBarbeiroSelecionado(barbeiroState.id);
                        setAgenda(barbeiroState.horarios);
                        const listaServicos = await buscarServicos(barbeiroState.id);
                        setServicos(listaServicos);
                    }
                }
            } catch (err) {
                console.error('Erro ao carregar barbearias:', err);
            } finally {
                setCarregandoBarbearia(false);
            }
        }

        carregarBarbearias();
    }, [barbeariaState, barbeiroState]);

    return (
        <>
            <ArrowReturn route={ROUTES.HOME} />
            <Header titulo="Novo Agendamento" />
            <div className="form-group">
                <label className="form-label">Barbearia</label>
                {carregandoBarbearia ? (
                    <p>Carregando barbearias...</p>
                ) : (
                    <select
                        value={barbeariaSelecionada}
                        onChange={async (e) => {
                            const idBarbearia = e.target.value;
                            setBarbeariaSelecionada(idBarbearia);
                            const listaBarbeiros = await buscarBarbeirosPorBarbearia(idBarbearia);
                            setBarbeiros(listaBarbeiros);
                            setBarbeiroSelecionado('');
                            setServicos([]);
                        }}
                    >
                        <option>Selecione uma barbearia</option>
                        {barbearias.map((barbearia) => (
                            <option key={barbearia.id} value={barbearia.id}>
                                {barbearia.nome}
                            </option>
                        ))}
                    </select>
                )}
            </div>

            {barbeiros.length > 0 && (
                <div className="form-group">
                    <label className="form-label">Profissional</label>
                    <select
                        value={barbeiroSelecionado}
                        onChange={async (e) => {
                            const idBarbeiro = e.target.value;
                            setBarbeiroSelecionado(idBarbeiro);
                            const listaServicos = await buscarServicos(idBarbeiro);
                            setServicos(listaServicos);
                            setServicoSelecionado('');
                        }}
                    >
                        <option>Selecione um barbeiro</option>
                        {barbeiros.map((barbeiro) => (
                            <option key={barbeiro.id} value={barbeiro.id}>
                                {barbeiro.nomeUsuario}
                            </option>
                        ))}
                    </select>
                </div>
            )}

            {servicos.length > 0 && (
                <div className="form-group">
                    <label className="form-label">Serviços</label>
                    <select
                        value={servicoSelecionado}
                        onChange={(e) => setServicoSelecionado(e.target.value)}
                    >
                        <option>Selecione um serviço</option>
                        {servicos.map((servico) => (
                            <option key={servico.id} value={servico.id}>
                                {servico.nomeTipoServico + ' R$ ' + servico.preco}
                            </option>
                        ))}
                    </select>
                </div>
            )}


            <div className="form-group">
                <label className="form-label">Dia da semana:</label>
                <input
                    type="date"
                    value={diaSelecionado}
                    onChange={(e) => setDiaSelecionado(e.target.value)}
                />
                <p>Dia selecionado: {diaSelecionado}</p>
            </div>


        </>
    );
}

export default NovoAgendamento;
