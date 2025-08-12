import axios from 'axios';

export async function buscarBarbearia() {
  try {
    const response = await axios.get('http://192.168.0.106:8080/barbearias/barbeiros');
    return response.data;
  } catch (error) {
    console.log('Erro ao buscar barbearias:', error);
    throw error;
  }
}

export async function buscarBarbeariaPorId(id) {
  try {
    const response = await axios.get(`http://192.168.0.106:8080/barbearias/${id}`);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar barbearia por ID:', error);
    throw error;
  }
}

export async function buscarBarbeirosPorBarbearia(idBarbearia) {
  const lista = await buscarBarbearia();
  const barbearia = lista.find(b => b.id === Number(idBarbearia));
  if (!barbearia) {
    throw new Error(`Barbearia com id ${idBarbearia} não encontrada.`);
  }
  return barbearia.barbeiros;
}


export function buscarServicos(idBarbeiro) {

  const servicosPorBarbeiro = {
    10: [
      { id: 4, tipo: 'Corte', valor: '35,00'},
      { id: 5, tipo: 'Corte + sombrancelha', valor: '40,00'},
      { id: 6, tipo: 'Corte + barba', valor: '45,00'}
    ],

    11: [
      { id: 4, tipo: 'Corte', valor: '35,00'},
      { id: 5, tipo: 'Corte + sombrancelha', valor: '40,00'}
    ],

    20: [
      { id: 4, tipo: 'Corte', valor: '35,00'},
    ],

    21: [
      { id: 4, tipo: 'Corte', valor: '35,00'},
      { id: 5, tipo: 'Corte + sombrancelha', valor: '40,00'},
      { id: 6, tipo: 'Corte + barba', valor: '45,00'}
    ],

    30: [
      { id: 4, tipo: 'Corte', valor: '35,00'},
      { id: 5, tipo: 'Corte + sombrancelha', valor: '40,00'},
      { id: 7, tipo: 'Platinado', valor: '90,00'}
    ]
  };

  return servicosPorBarbeiro[idBarbeiro] || [];
}

