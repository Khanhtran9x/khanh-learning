const MainStockList = () => {
  return (
    <>
      <table className="table table-hover">
        <thead>
          <tr>
            <th className="col-1">#</th>
            <th className="col-4">Tên</th>
            <th className="col-2">Giá</th>
            <th className="col-2">Thay đổi</th>
            <th className="col-1">Biểu đồ</th>
            <th className="col-1">Giao dịch</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>1</th>
            <td>VNI</td>
            <td>1000</td>
            <td>2.12%</td>
            <td>@mdo</td>
            <td><button className="primary-button">Mua</button></td>
          </tr>
          <tr>
            <th>2</th>
            <td>SSI</td>
            <td>50.000đ</td>
            <td>2.12%</td>
            <td>@mdo</td>
            <td><button className="primary-button">Mua</button></td>
          </tr>
          <tr>
            <th>3</th>
            <td>HPG</td>
            <td>70.000đ</td>
            <td>3.12%</td>
            <td>@mdo</td>
            <td><button className="primary-button">Mua</button></td>
          </tr>
          <tr>
            <th>4</th>
            <td>VHM</td>
            <td>30.000đ</td>
            <td>2.14%</td>
            <td>@mdo</td>
            <td><button className="primary-button">Mua</button></td>
          </tr>
        </tbody>
      </table>
    </>
  )
}

export default MainStockList