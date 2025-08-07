import ArrowReturn from "../../../components/common/ArrowReturn/ArrowReturn";
import Header from "../../../components/common/Header/Header";
import Container from "../../../components/common/Container/Container";
import { buscarBarbearia, buscarBarbeirosPorBarbearia, buscarServicos } from "../../../services/agendamentoServices";
import './NovoAgendamento.css';
import { useState, useEffect } from "react";

const NovoAgendamento = () => {

    const [barbearias, setBarbearia] = useState([]);
    const [barbeariaSelecionada, setBarbeariaSelecionada] = useState('');
    const [carregandoBarbearia, setCarregandoBarbearia] = useState(false);

    const [barbeiroSelecionado, setBarbeiroSelecionado] = useState('');
    const [barbeiros, setBarbeiros] = useState([]);

    const [servicos, setServicos] = useState([]);
    const [servicoSelecionado, setServicoSelecionado] = useState('');

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
            <ArrowReturn />
            <Header titulo="Novo Agendamento" />
            <Container >
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
                        <label className="form-label">Barbeiro</label>
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
                                    {barbeiro.nome}
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
                            onChange={async (e) => {
                                const idServico = e.target.value;
                                setServicoSelecionado(idServico);
                            }}
                        >
                            <option>Selecione um serviço</option>
                            {servicos.map((servico) => (
                                <option key={servico.id} value={servico.id}>
                                    {servico.tipo}
                                </option>
                            ))}
                        </select>
                    </div>
                )}


            </Container >

        </>
    );
}

export default NovoAgendamento;