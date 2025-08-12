import { FaArrowLeft } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import './ArrowReturn.css';

const ArrowReturn = ({ route }) => {
    return (
        <Link to={route}>
            <button className='arrow-return'>
                <FaArrowLeft />
            </button>
        </Link>

    )
}

export default ArrowReturn;