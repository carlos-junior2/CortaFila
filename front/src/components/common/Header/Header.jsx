import './Header.css';

/**
 * Componente Header
 * 
 * Componente de cabeçalho utilizado para exibir uma saudação ou título na parte superior das páginas da aplicação.
 * 
 * @component
 * @returns {JSX.Element} Elemento JSX que renderiza o cabeçalho com uma mensagem de boas-vindas.
 * 
 * @example
 * <Header />
 * 
 * @description
 * 
 * - Componente simples de cabeçalho contendo:
 *   - Um contêiner com a classe `card-header` para estilização.
 *   - Um elemento `<h1>` com a mensagem "Bem-vindo!".
 * 
 * - Pode ser reutilizado em diversas páginas da aplicação como elemento de boas-vindas ou introdução de seção.
 * 
 * @see Home
 */


const Header = ({ titulo }) => {
    return (
        <div className='card-header'>
            <h1>{titulo}</h1>
        </div>

    )
}

export default Header;
