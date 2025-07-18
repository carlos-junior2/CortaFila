import './PageWrapper.css';

/**
 * Componente responsável por centralizar e estilizar visualmente formulários na tela.
 *
 * Este componente envolve o conteúdo do formulário em um layout centralizado, utilizando o `PageWrapper`
 * para garantir alinhamento vertical e horizontal ao centro da tela.
 * Ele aplica uma estrutura visual consistente, com largura máxima, fundo branco,
 * bordas arredondadas e sombra, sendo ideal para telas como login, cadastro e recuperação de senha.
 *
 * @param {React.ReactNode} children - Elementos filhos que serão renderizados dentro do container do formulário.
 */

const PageWrapper = ({ children }) => {
    return (
        <div className="page-wrapper">
            {children}
        </div>
    )
}
export default PageWrapper;