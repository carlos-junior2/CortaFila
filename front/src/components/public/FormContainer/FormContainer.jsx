import PageWrapper from "../PageWrapper/PageWrapper"
import './FormContainer.css';

/**
 * Componente responsável por estruturar e estilizar visualmente um formulário dentro da aplicação.
 *
 * Este componente envolve qualquer conteúdo passado como `children` em uma caixa centralizada com layout consistente,
 * utilizando o `PageWrapper` para alinhar o conteúdo ao centro da tela.
 * Ele aplica estilos como fundo branco, bordas arredondadas, espaçamento interno e sombra,
 * sendo ideal para formulários como login, cadastro, recuperação de senha, entre outros.
 *
 * @param {React.ReactNode} children - Elementos que serão renderizados dentro do container do formulário.
 * @returns {JSX.Element} Container centralizado e estilizado com o conteúdo fornecido.
 */

const FormContainer = ({ children }) => {
    return (
        <PageWrapper>
            <div className="form-container">
                {children}
            </div>
        </PageWrapper >

    )
}

export default FormContainer