import './PublicHeader.css';

const PublicHeader = ({ titulo, icone }) => {
    return (
        <div className="header-feed">
            <h1>{titulo}</h1>
            <img src={icone} />
        </div>
    )
}

export default PublicHeader;