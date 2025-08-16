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


export async function buscarServicos(idBarbeiro) {

  try {
    const servicos = await axios.get(`http://192.168.0.106:8080/barbeiroServicos/${idBarbeiro}`);
    return servicos.data;
  } catch (error) {
    console.log('Erro ao buscar barbeiros por ID:', error)
    throw error;
  }

}

