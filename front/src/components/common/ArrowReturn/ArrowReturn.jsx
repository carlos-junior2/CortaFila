import { FaArrowLeft } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { ROUTES } from '../../../routes/constantsRoutes';
import './ArrowReturn.css';

const ArrowReturn = () => {
    return (
        <Link to={ROUTES.HOME}>
            <button className='arrow-return'>
                <FaArrowLeft />
            </button>
        </Link>

    )
}

export default ArrowReturn;