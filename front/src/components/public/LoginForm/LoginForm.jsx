import { useState } from "react";
import { login } from "../../../auth/AuthLogin";
import { Link, useNavigate } from 'react-router-dom';
import { ROUTES } from "../../../routes/constantsRoutes";
import './LoginForm.css'
import { validateEmail } from "../../../utils/validations";

/**
 * Componente responsável por renderizar o formulário de login do sistema.
 *
 * Este componente permite que o usuário informe seu e-mail e senha para autenticação.
 * Ele gerencia o estado dos campos com `useState`, realiza o controle de entrada dos dados
 * e apresenta um botão de envio do formulário.
 *
 * O layout e o estilo do formulário são definidos pelo arquivo `LoginForm.css`,
 * e os campos são marcados como obrigatórios para evitar envio com dados vazios.
 *
 * Obs.: A lógica de autenticação (ex: chamada para API ou validação) ainda pode ser implementada na submissão do formulário.
 *
 * @component
 * @returns {JSX.Element} Formulário de login com campos de e-mail, senha e botão de envio.
 */


const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [senha, setSenha] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('handleSubmit foi chamado');

        if (!username.trim() || !senha.trim()) {
            setError("Preencha todos os campos.")
            return;
        }

        try {
            const token = await login(username, senha);
            localStorage.setItem('authToken', token);

            console.log('Login bem-sucedido, redirecionando...');

            navigate('/');
        } catch (error) {
            setError("Erro ao fazer login");
        }

    }

    return (
        <>
            <form className="form" onSubmit={handleSubmit}>
                <h2>Seja bem-vindo</h2>
                <div className="form-group">
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="input-form"
                        required
                    />
                </div>

                <div className="form-group">
                    <input
                        type="password"
                        placeholder="Senha"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        className="input-form"
                        required
                    />
                </div>

                <button type="submit" className="button-submit">
                    Login
                </button>

            </form>
        </>
    )
}

export default LoginForm;