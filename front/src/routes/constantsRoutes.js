/* src\routes\constantsRoutes.js */

export const ROUTES = {
    /* Rotas públicas */
    HOME: '/',
    LOGIN: '/auth/login',
    INDEX: '/index',
    FEED: '/feed',
  
    /* Rotas protegidas */
    CADASTRAR_BARBEARIA: '/cadastrar/barbearia',
    AGENDAMENTO: '/novo-agendamento',
    NOTIFICACOES: '/notificacoes',
    PERFIL: '/meu-perfil',
    
    /* Rota de erro */
    ERROR_404: '*',
  };