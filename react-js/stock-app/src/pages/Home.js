import { Button } from "bootstrap";
import Header from "../components/Header";
import phone from '../images/phone.png';
import MainStockList from "../components/MainStockList";

const Home = () => {
  return (
    <>
      <Header />
      <div className="container">
        <div className="row">
          <div className="col-6">
            <div className="box-text">
              <div className="row">
                <h1 className="primary-title">Bắt đầu</h1>
              </div>
              <div className="row">
                <h1 className="primary-title">đầu tư</h1>
              </div>
              <div className="row">
                <h1 className="primary-title">ngay hôm nay</h1>
              </div>
              <div className="row mt-2">
                <h5 className="primary-content">DreamX là một nền tảng đầu tư an toàn</h5>
              </div>
              <div className="row">
                <h5 className="primary-content">Hãy đăng kí cho mình một tài khoản và bắt đầu</h5>
              </div>
              <div className="row mt-2">
                <button className="giant-button">Đăng kí</button>
                <button className="giant-button ml-3">Đăng nhập</button>
              </div>
            </div>

          </div>
          <div className="col-6">
            <div className="row justify-item-center">
              <img src={phone} className="ml-5" />
            </div>
          </div>
        </div>

        <div className="row">
          <MainStockList />
        </div>
      </div>
    </>
  )
}

export default Home