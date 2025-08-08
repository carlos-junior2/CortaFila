import './Feed.css';
import Footer from "../../../components/common/Footer/Footer";
import HeaderFeed from "../../../components/public/HeaderFeed/HeaderFeed";
import barberShop1 from "../../../assets/barberShop1.jpg";
import barberShop2 from "../../../assets/barberShop2.jpg";
import barberShop3 from "../../../assets/barberShop3.jpg";
import barberShop4 from "../../../assets/barberShop4.jpg";
import options from '../../../assets/options.png'

const Feed = () => {

    return (
        <>
            <HeaderFeed titulo="CortaFila" icone={options} />
            <div className="feed">
                <div className="card">
                    <img src={barberShop1} alt="barbearia 1" />
                    <div className="card-content">
                        <h3>Barbearia do João</h3>
                        <p>⭐ 4.8 120</p>
                        <p>📍 1.2 km</p>
                    </div>
                </div>

                <div className="card">
                    <img src={barberShop2} alt="barbearia 2" />
                    <div className="card-content">
                        <h3>Barbearia do Bairro</h3>
                        <p>⭐ 5.0 120</p>
                        <p>📍 0.5 km</p>
                    </div>
                </div>

                <div className="card">
                    <img src={barberShop3} alt="barbearia 3" />
                    <div className="card-content">
                        <h3>Studio Barbers</h3>
                        <p>⭐ 3.0 (80 avaliações)</p>
                        <p>📍 2.0 km</p>
                    </div>
                </div>

                <div className="card">
                    <img src={barberShop4} alt="barbearia 4" />
                    <div className="card-content">
                        <h3>Barber King</h3>
                        <p>⭐ 4.9 (200 avaliações)</p>
                        <p>📍 0.8 km</p>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    )

}

export default Feed;