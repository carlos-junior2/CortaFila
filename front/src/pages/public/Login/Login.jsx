import FormContainer from "../../../components/public/FormContainer/FormContainer";
import LoginForm from "../../../components/public/LoginForm/LoginForm";

/**
 * Página Login
 * 
 * Página de autenticação onde os usúarios do sistema podem realizar login para gerar um token e acessar
 * outras páginas da aplicação.
 * 
 * @component
 * @returns {JSX.Element} Estrutura da página de login, incluindo logotipo, plano de fundo e formulário de autenticação.
 * 
 * @example
 * <Login />
 * 
 * @description
 * 
 * - Inclui os seguintes componentes principais:
 *   - **FormContainer**: Container de tamanho padrão da aplicação para colocar os campos de formularios.
 *   - **LoginForm**: Inclui os campos de login(email e senha) e o botão .
 * 
 * @see FormContainer
 * @see LoginForm
 */

const Login = () => {

    return (
        <>
            <FormContainer>
                <LoginForm />
            </FormContainer>
        </>
    );
};

export default Login