
class Locations extends React.Component {
  constructor(props){
    super(props) 
      this.state = {
        data: []
       }    
  }
  componentDidMount() {
    $.ajax({
       url: "/fncs/locations",
       type: "GET",
       dataType: 'json',
       ContentType: 'application/json',
       success: function(data) {
         this.setState({data: data});
         this.props.locationIdSetter(data[0].id);
       }.bind(this),
       error: function(jqXHR) {
         console.log(jqXHR);
       }.bind(this)
    })
  }
  
 
  render() {
	    
 	    return (
	     <select onChange={this.props.handler}>
	      {this.state.data.map(function(item, key) {
	               return (
	                 <option value={item.id} on>                 
	                     {item.city}, {item.province}, {item.country}
		                      </option>
	                )
	             })}
	       </select>
	    )
	  }
}

class Trips extends React.Component {
	  constructor(props){
	    super(props) 
	    this.handler = this.handler.bind(this)
	    this.setLocationId = this.setLocationId.bind(this)
	    this.setTripData = this.setTripData.bind(this);
	    this.state = {
	    	data:[],
	    	locationId:-1
	    }
	  }
	  
	  handler(e) {
		  this.state.locationId=e.target.value;
		  this.setTripData();
		  }
	  
	  setLocationId(locationIdValue){
		  this.state.locationId=locationIdValue;
		  this.setTripData();
	  }
	  
	  setTripData(){
		  $.ajax({
		       url: "/fncs/trips?locationId="+this.state.locationId,
		       type: "GET",
		       dataType: 'json',
		       ContentType: 'application/json',
		       success: function(data) {
		         this.setState({data: data});
		       }.bind(this),
		       error: function(jqXHR) {
		         console.log(jqXHR);
		       }.bind(this)
		    })
		    
	  }
	  
	  
	  
	  render() {		    
	 	    return (
	 	    		<div>
		     <Locations locationIdSetter= {this.setLocationId} handler = {this.handler}/>
		     <table>
		      {this.state.data.map(function(item, key) {
		               return (
		                 <tr>
		                 <td>{item.tripDate}</td>
		                 <td>{item.location.city}</td>
		                 <td>{item.location.province}</td>
		                 <td>{item.location.country}</td>
		                
		                 <td onClick=><a href="/fncs/trips/join?id={item.id}">{item.id}</a></td>
		                 </tr>
		                )
		             })}
		       </table>
		       </div>
		    )
		  }
}
ReactDOM.render(<Trips/>, document.getElementById('app'))