import React, { useState } from 'react';
import { FaCut, FaClock, FaUser } from 'react-icons/fa';


const barbearias = [
  { id: 1, nome: 'Barbearia Estilo' },
  { id: 2, nome: 'Top Blade' },
  { id: 3, nome: 'Do João' },
];

const barbeirosPorBarbearia = {
  1: ['Lucas', 'André'],
  2: ['Rafael', 'Diego'],
  3: ['João', 'Pedro'],
};

const horariosPorBarbeiro = {
  Lucas: ['09:00', '10:30', '14:00'],
  André: ['11:00', '15:00'],
  Rafael: ['10:00', '13:30'],
  Diego: ['09:30', '16:00'],
  João: ['08:00', '12:00'],
  Pedro: ['13:00', '17:00'],
};

export default function App() {
  const [barbeariaSelecionada, setBarbeariaSelecionada] = useState(null);
  const [barbeiroSelecionado, setBarbeiroSelecionado] = useState(null);
  const [horarioSelecionado, setHorarioSelecionado] = useState(null);

  const barbeiros = barbeirosPorBarbearia[barbeariaSelecionada] || [];
  const horarios = horariosPorBarbeiro[barbeiroSelecionado] || [];

  const handleAgendar = () => {
    if (barbeariaSelecionada && barbeiroSelecionado && horarioSelecionado) {
      alert(`✅ Agendado com sucesso!
Barbearia: ${barbearias.find(b => b.id === barbeariaSelecionada).nome}
Barbeiro: ${barbeiroSelecionado}
Horário: ${horarioSelecionado}`);
    } else {
      alert("⚠️ Preencha todas as etapas para agendar.");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center px-4">
      <div className="w-full max-w-3xl bg-white rounded-3xl shadow-2xl p-8 space-y-8">
        <header className="text-center">
          <h1 className="text-4xl font-bold text-primary">CortaFila</h1>
          <p className="text-gray-600 mt-2 text-lg">Agende seu horário com facilidade</p>
        </header>

        {/* Etapa 1 */}
        <section>
          <h2 className="text-xl font-semibold text-gray-800 mb-3 flex items-center gap-2">
            <FaCut className="text-primary" /> Escolha a barbearia
          </h2>
          <select
            className="w-full p-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary"
            onChange={(e) => {
              setBarbeariaSelecionada(Number(e.target.value));
              setBarbeiroSelecionado(null);
              setHorarioSelecionado(null);
            }}
            value={barbeariaSelecionada || ''}
          >
            <option value="" disabled>Selecione uma barbearia</option>
            {barbearias.map(b => (
              <option key={b.id} value={b.id}>{b.nome}</option>
            ))}
          </select>
        </section>

        {/* Etapa 2 */}
        {barbeiros.length > 0 && (
          <section>
            <h2 className="text-xl font-semibold text-gray-800 mb-3 flex items-center gap-2">
              <FaUser className="text-primary" /> Escolha o barbeiro
            </h2>
            <div className="grid grid-cols-2 sm:grid-cols-3 gap-4">
              {barbeiros.map(barbeiro => (
                <button
                  key={barbeiro}
                  onClick={() => {
                    setBarbeiroSelecionado(barbeiro);
                    setHorarioSelecionado(null);
                  }}
                  className={`p-4 rounded-xl transition text-center font-medium shadow-sm border ${
                    barbeiroSelecionado === barbeiro
                      ? 'bg-primary text-white border-primary'
                      : 'bg-white hover:bg-gray-50 border-gray-300'
                  }`}
                >
                  {barbeiro}
                </button>
              ))}
            </div>
          </section>
        )}

        {/* Etapa 3 */}
        {horarios.length > 0 && (
          <section>
            <h2 className="text-xl font-semibold text-gray-800 mb-3 flex items-center gap-2">
              <FaClock className="text-primary" /> Escolha o horário
            </h2>
            <div className="flex flex-wrap gap-3">
              {horarios.map(horario => (
                <button
                  key={horario}
                  onClick={() => setHorarioSelecionado(horario)}
                  className={`px-4 py-2 rounded-lg border shadow-sm font-medium transition ${
                    horarioSelecionado === horario
                      ? 'bg-primary text-white border-primary'
                      : 'bg-white hover:bg-gray-50 border-gray-300'
                  }`}
                >
                  {horario}
                </button>
              ))}
            </div>
          </section>
        )}

        {/* Agendar */}
        <section>
          <button
            onClick={handleAgendar}
            className="w-full bg-primary text-white py-4 rounded-xl text-lg font-bold hover:bg-red-800 transition"
          >
            Agendar
          </button>
        </section>
      </div>
    </div>
  );
}
