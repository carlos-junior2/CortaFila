import api from "../services/axiosConfig"

/**
 * Função para realizar o login do usuário.
 * Envia os dados de e-mail e senha para a API e armazena o token de autenticação no localStorage.
 * @param {string} email - E-mail do usuário.
 * @param {string} password - Senha do usuário.
 * @returns {string} - Retorna o token de autenticação se o login for bem-sucedido.
 * @throws {Error} - Lança um erro caso o login falhe, com uma mensagem de erro.
 */

export const login = async (username, password) => {
    try {
        const response = await api.post('/auth/login', {
            username,
            password
        });

        if (response.status === 200) {
            const { token } = response.data;

            localStorage.setItem('authToken', token);

            return token;
        } else {
            throw new Error("Erro ao fazer login");
        }
    } catch (error) {
        throw new Error(error.response?.data?.message || "Erro ao fazer login");
    }
};