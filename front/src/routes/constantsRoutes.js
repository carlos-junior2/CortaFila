/* src\routes\constantsRoutes.js */

export const ROUTES = {
    /* Rotas públicas */
    HOME: '/',
    LOGIN: '/auth/login',
    INDEX: '/index',
  
    /* Rotas protegidas */
    CADASTRAR_BARBEARIA: '/cadastrar/barbearia',
    AGENDAMENTO: '/novo-agendamento',
    
    /* Rota de erro */
    ERROR_404: '*',
  };