import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import AddEmployee from './component/AddEmployee';
import EditEmployee from './component/EditEmployee';
import EmployeeList from './component/EmployeeList';
import NavigationBar from './component/NavigationBar';



function App() {
  return <>
  <BrowserRouter>
  <NavigationBar/>
  <Routes>
  <Route index element ={<EmployeeList />}></Route>
    <Route path='/' element ={<EmployeeList />}></Route>
    <Route path="/employeeList" element ={<EmployeeList />}></Route>
    <Route path="/addEmployee" element ={<AddEmployee />}></Route>
    <Route path="/editEmployee/:id" element ={<EditEmployee />}></Route>
  </Routes>
  </BrowserRouter>
  </>
};

export default App;
