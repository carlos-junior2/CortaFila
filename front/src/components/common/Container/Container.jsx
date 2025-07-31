import './Container.css';

/**
 * Componente Container
 * 
 * Componente de layout que serve como contêiner padrão da aplicação, responsável por centralizar e limitar a largura do conteúdo.
 * 
 * @component
 * @param {Object} props
 * @param {React.ReactNode} props.children - Elementos filhos que serão renderizados dentro do contêiner.
 * 
 * @returns {JSX.Element} Elemento JSX que encapsula o conteúdo da página dentro de uma div com classe `container`.
 * 
 * @example
 * <Container>
 *   <p>Conteúdo centralizado</p>
 * </Container>
 * 
 * @description
 * 
 * - Utilizado em diversas telas da aplicação para garantir consistência visual e alinhamento.
 * - Aplica estilos globais definidos em `Container.css`, como margens, paddings e largura máxima.
 * - Aceita qualquer conteúdo como filho (`children`), oferecendo flexibilidade no uso.
 */


const Container = ({ children }) => {
    return (
        <div className="container">
            {children}
        </div>
    )
}

export default Container;