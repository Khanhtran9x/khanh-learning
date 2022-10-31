import Button from '@mui/material/Button';
import logo from '../images/logo.png';

const Header = () => {
    return (
        <>
            <div className="container-fluid header-container">
                <div className="container">
                    <div className="row">
                        <div className="col-2">
                            <img className="navbar-logo" width="70%" src={logo} />
                        </div>
                        <div className="col-10 navbar-container">
                            <p className="navbar-item">Tin tức</p>
                            <p className="navbar-item">Thị trường</p>
                            <p className="navbar-item">Thông tin</p>
                            <p className="navbar-item">Đầu tư</p>
                            <p className="navbar-item">Đăng kí</p>
                            <button className="navbar-item-button primary-button">Đăng nhập</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Header