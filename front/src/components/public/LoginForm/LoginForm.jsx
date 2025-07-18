import { useState } from "react";
import './LoginForm.css'

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
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    return (
        <>
            <form className="form">
                <h2>Seja bem-vindo</h2>
                <div className="form-group">
                    <input
                        type="email"
                        placeholder="E-mail"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
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