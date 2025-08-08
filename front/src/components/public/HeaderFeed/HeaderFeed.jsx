import './HeaderFeed.css';
import options from '../../../assets/options.png'
import PublicHeader from '../../common/PublicHeader/PublicHeader';

const HeaderFeed = () => {
    return (
        <>
            <PublicHeader titulo="CortaFila" icone={options} />

            <div class="search-container">
                <input type="text" placeholder="Buscar barbearia..." />
            </div>

            <div className='filters'>
                <button>⭐ 4+ Estrelas</button>
                <button>💰 Preço</button>
                <button>📍 Perto de mim</button>
                <button>⏱ Disponível hoje</button>
                <button>💈 Serviços</button>
            </div>
        </>

    )
}

export default HeaderFeed;