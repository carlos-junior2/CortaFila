import axios from 'axios';

export function buscarBarbearia() {

  return [
    { id: 1, nome: 'Barbearia do João' },
    { id: 2, nome: 'Studio Barbers' },
    { id: 3, nome: 'Barber King' }

    ]

}

export function buscarBarbeirosPorBarbearia(idBarbearia) {
  const barbeirosPorBarbearia = {
    1: [
      { id: 10, nome: 'João' },
      { id: 11, nome: 'Carlos' }
    ],
    2: [
      { id: 20, nome: 'Lucas' },
      { id: 21, nome: 'Pedro' }
    ],
    3: [
      { id: 30, nome: 'Henrique' }
    ]
  };

  return barbeirosPorBarbearia[idBarbearia] || [];
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
