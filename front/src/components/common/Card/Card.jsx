import './Card.css';

/**
 * Componente Card
 * 
 * Componente visual que exibe informações a depender da tela que esta sendo exibida de forma destacada.
 * 
 * @component
 * @returns {JSX.Element} Elemento JSX que renderiza um card com data, hora e tipo de serviço agendado.
 * 
 * @example
 * <Card />
 * 
 * @description
 * 
 * - Utilizado na página Home para mostrar o próximo agendamento do usuário.
 * - Estrutura do componente:
 *   - `card-container`: Wrapper externo com título da seção.
 *   - `card`: Bloco visual que contém as informações do compromisso, como data e tipo de serviço.
 * 
 * - Estilizado com classes definidas em `Card.css`.
 * 
 * @see Home
 */


const Card = () => {
    return (
        <div className='card-container'>
            <h2>Próximo compromisso</h2>
            <div class="card">
                <p><strong>May 3, 10:00</strong></p>
                <p>Haircut</p>
            </div>
        </div>
    )

}

export default Card;