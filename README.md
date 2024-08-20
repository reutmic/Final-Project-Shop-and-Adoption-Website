<h1>Java Spring Project</h1>

<h4>Made by: Reut Michaeli</h4>

<h2>Website functionality</h1>
<p>For this exercise, I made a website for pets adoption and a shop.</p>
<p>I used Spring Security and created two users:</p>
<ol>
  <li>
    <h4>User</h4>
    <p>Username: 'user' , password: 'user'.</p>
    <p>The user can see pets for adoption, fill a form to schedule a visit to see the pet they think to adopt, and check their visit requests' status.
      In addition, the user can browse products in the shop, add/remove them to the cart, checkout and track their orders.</p>
  </li>
  <li>
    <h4>Admin</h4>
    <p>Username: 'admin' , password: 'admin'.</p>
    <p>The admin can add/remove/update shop products and pets for adoption. Admin can also manage orders and pets visit requests: change their status or delete them. 
    When adding a new product/pet, there is a mandatory 'image' field. All the images have to be from the 'images' folder for them to be displayed. The value for the 
    'image' field should be a full image name (including extension) from the 'images' folder. I added default images and their full name is displayed right below 
    the 'image' filed in those forms.</p>
  </li>
</ol>
<h4>Main Website Pages</h4>
<p>There is a menu which allows users to go to the different website pages, based on the role of the user they logged in with (admin or user).</p>
<ul>
  <li>
    <h5>Adopt Page (user and admin)</h5>
    <p>In this page pets for adoption will be displayed. Admin can add a new pet, update a pet, or remove a pet from the page 
      (by clicking on the button "I was adopted"). The user will see a "Let's Meet" button for each pet, which will lead to a page with a form they can fill in 
    order to schedule a visit request to see the pet. The user can check the request status by the email address they provided.</p>
  </li>
  <li>
    <h5>Shop Page (user and admin)</h5>
    <p>In this page all products will be displayed. There is an option to filter products by their category and the animal they are suited for. 
      Admin can update/delete products and add a new product. User can add products to their cart, as long as they are in stock.</p>
  </li>
  <li>
    <h5>Cart Page (user)</h5>
    <p>In this page, the user's cart will be presented and they will be able to see all the products in the cart: the quantity, stock, and price for each product and 
      other details. The user can remove prodcuts from the cart, and go to the checkout page, where they can fill a form to place the order. Then, they will 
    get an order number.</p>
  </li>
  <li>
    <h5>Track Order Page (user)</h5>
    <p>In this page, the user can enter their order number and check the order's status.</p>
  </li>
  <li>
    <h5>My Visits Page (user)</h5>
    <p>In this page, the user will be able to see the status of their pet visits request, by entering the email address they used in the visit request form.
    The user can cancel a request. Requests with statuses 'cancelled' or 'done' will not be displayed. Cancelling a visit request will not delete it, but will change 
    its status to "Cancelled".</p>
  </li>
  <li>
    <h5>Manage Orders Page (admin)</h5>
    <p>In this page, all orders made by users will be displayed. The admin can change their status or delete them. </p>
  </li>
  <li>
    <h5>Manage Pet Visits Page (admin)</h5>
     <p>In this page, all pets visit requests made by users will be displayed. The admin can change their status and add a comment for the user to see, or delete them. 
       The admin cannot change a visit request status to 'Cancelled' (This can be done only by the user when they cancel their visit request), and the admin will not 
       be able to change the status of cancelled visit requests. </p>
  </li>
</ul>

<h1>Execution</h1>
<p>
In XAMPP, start the 'Apache' and 'MySQL' services. Then, run the project and open 'http://localhost:8080/' in the browser.
</p>
<p>There is an initialization file named 'data.sql' in the 'resources' folder. This file is needed to initialize the website's products and pets DB tables.</p>
<p>I used Spring Security default login/logout pages. When entering the website, the login page will appear. 
Credentials: Admin role: Username: 'admin', password: 'admin' . User role: Username: 'user', password: 'user'.</p>

